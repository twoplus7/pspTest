/**
 * 
 */
package com.psp.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.psp.common.ResponseData;
import com.psp.dto.EventCategoryDto;
import com.psp.dto.EventDto;
import com.psp.dto.EventUserDto;
import com.psp.enums.AdminStatus;
import com.psp.enums.HttpStatus;
import com.psp.exception.PspException;
import com.psp.model.EventCategory;
import com.psp.model.Events;
import com.psp.model.User;
import com.psp.repository.EventCategoryRepository;
import com.psp.repository.EventRepository;
import com.psp.repository.UserRepository;
import com.psp.service.IEventService;

/**
 * @author us
 * 
 */

@Service
public class EventServiceImpl implements IEventService {

	@Resource
	EventRepository eventRepository;

	@Resource
	EventCategoryRepository eventCategoryRepository;

	@Resource
	UserRepository userRepo;

	@Override
	public ResponseData createEvent(EventDto eventDto) {
		Events event = new Events();
		event.setDataFromData(eventDto);
		String generatedSlug = generateSlug(eventDto.getEventSlug(), eventDto.getEventTitle(), Boolean.FALSE,
				eventDto.getEventId());
		event.setEventSlug(generatedSlug);
		if (eventDto.getEventId() == null || eventDto.getEventId().intValue() == 0) {
			event.setEventStatus(AdminStatus.NOT_APPROVED);
		}
		event.setCreatedBy("GET NAME FROM SESSION");
		eventRepository.save(event);
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
	}

	@Override
	public String generateSlug(String eventSlug, String eventTitle, Boolean isBlog, Long id) {
		if (eventSlug != null) {
			if (Boolean.TRUE.equals(isBlog)) {
				if (id != null) {
					if (eventRepository.findById(id).get().getEventSlug().equals(eventSlug))
						return eventSlug;
				}
			} else {
				if (id != null) {
					if (eventRepository.findById(id).get().getEventSlug().equals(eventSlug))
						return eventSlug;
				}
			}
		}
		return (eventSlug == null) ? generateSlugName(eventTitle, isBlog) : generateSlugName(eventSlug, isBlog);
	}

	private String generateSlugName(String name, Boolean isBlog) {
		List<String> slugList;
		if (Boolean.TRUE.equals(isBlog)) {
			slugList = eventRepository.getAllSlug();
		} else {
			slugList = eventRepository.getAllSlug();
		}
		StringBuilder newSlug = new StringBuilder(name.replaceAll("\\s", "-"));
		while (slugList.contains(newSlug.toString())) {
			String appendString = RandomStringUtils.random(1, "0123456789");
			newSlug.append("-" + appendString);
		}
		return newSlug.toString();
	}

	@Override
	public Events getEventById(Long eventId) throws PspException {
		Optional<Events> eventResp = eventRepository.findById(eventId);
		if (!eventResp.isPresent()) {
			throw new PspException(HttpStatus.ID_NOT_VALID, "Invalid Id");
		}
		return eventResp.get();
	}

	@Override
	public ResponseData getAllEvent(Boolean isApproved, Boolean isFeatured) {
		List<Events> eventList = new ArrayList<>();
		if (Boolean.TRUE.equals(isApproved)) {
			eventList = eventRepository.findByEventStatusAndIsFeatured(AdminStatus.APPROVED, isFeatured);
		} else if (isApproved == null || Boolean.FALSE.equals(isApproved)) {
			eventList = eventRepository.findAll();
		}
		List<EventUserDto> eventUserList = userBind(eventList);
		if (eventUserList.isEmpty()) {
			return new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage(), "Event list is empty");
		}
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventUserList);
	}

	@Override
	public List<EventUserDto> userBind(List<Events> eventList) {
		User user = new User();
		List<EventUserDto> eventUserList = new ArrayList<>();
		for (Events event : eventList) {
			EventUserDto eventUserDto = new EventUserDto();
			user = userRepo.findById(Long.valueOf(event.getCreatedBy())).get();
			eventUserDto.setEvents(event);
			eventUserDto.setUser(user);
			eventUserList.add(eventUserDto);
		}
		return eventUserList;
	}

	@Override
	public ResponseData searchEvent(String eventLocation, LocalDate eventDate, Long eventCategoryId) {

		List<Events> eventsList = eventRepository.findAll(new Specification<Events>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Events> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				Join<Events, EventCategory> eventCat = root.join("eventCategory");
				String eventCategoryName = null;

				if (eventLocation != null) {
					/*
					 * predicates
					 * .add(criteriaBuilder.and(criteriaBuilder.equal(root.get("eventLocation"),
					 * eventLocation)));
					 */
					predicates.add(criteriaBuilder
							.or(criteriaBuilder.like(root.get("eventLocation"), "%" + eventLocation + "%")));
				}

				/*
				 * Expression<String> df = root.get("eventDate").as(String.class); if (eventDate
				 * != null) predicates.add(criteriaBuilder.greaterThanOrEqualTo(df, dt));
				 */
				/*
				 * if (eventDate != null) { /* try { date = formatter.parse(eventDate); dt =
				 * formatter.format(date); } catch (ParseException e) { e.printStackTrace(); }
				 *
				 * Predicate startDt =
				 * criteriaBuilder.greaterThanOrEqualTo(root.get("eventStartDate"), eventDate);
				 * Predicate endDt = criteriaBuilder.lessThanOrEqualTo(root.get("eventEndDate"),
				 * eventDate);
				 * 
				 * predicates.add(criteriaBuilder.or(startDt, endDt));
				 * 
				 * }
				 */

				Optional<EventCategory> eventCat1 = null;
				if (eventCategoryId != null) {
					eventCat1 = eventCategoryRepository.findById(eventCategoryId);
					if (eventCat1.isPresent()) {
						eventCategoryName = eventCat1.get().getEventCategoryName();
					}
				}

				if (eventCategoryName != null)
					predicates.add(criteriaBuilder
							.and(criteriaBuilder.equal(eventCat.get("eventCategoryName"), eventCategoryName)));

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		});
		List<Events> resp = new ArrayList<>();
		if (eventDate != null) {
			for (Events e : eventsList) {
				if (e.getEventStartDate() != null && e.getEventEndDate() != null) {
					if (eventDate.isAfter(e.getEventStartDate()) || eventDate.isEqual(e.getEventStartDate())) {
						if (eventDate.isBefore(e.getEventEndDate()) || eventDate.isEqual(e.getEventEndDate())) {
							resp.add(e);
						}
					}
				}
			}
		} else {
			return (eventsList.isEmpty())
					? new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage(), "Result not found")
					: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventsList);
		}

		return (resp.isEmpty())
				? new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage(), "Result not found")
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), resp);

	}

	@Override
	public List<EventUserDto> getEventUserListByUser(Long userId) {
		List<Events> eventList = eventRepository.findByCreatedBy(String.valueOf(userId));
		List<EventUserDto> eventUserList = userBind(eventList);
		return eventUserList;
	}

	@Override
	public List<Events> getEventListByUser(Long userId) {
		List<Events> eventList = eventRepository.findByCreatedBy(String.valueOf(userId));
		return eventList;
	}

	@Override
	public ResponseData deleteEventById(Long eventId) {
		if (eventRepository.findById(eventId).isPresent()) {
			eventRepository.deleteById(eventId);
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(),
					"Event deleted successfully");
		}
		return new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage(), "Invalid event-Id");
	}

	@Override
	public ResponseData createEventCategory(EventCategoryDto dto) {
		com.psp.model.EventCategory eventCategory = new com.psp.model.EventCategory();
		eventCategory.setEventCategoryName(dto.getEventCategoryName());
		eventCategory.setEventCategoryStatus("ACTIVE");
		eventCategoryRepository.save(eventCategory);
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
	}

	@Override
	public ResponseData getEventCategoryById(Long eventCategoryId) {
		Optional<com.psp.model.EventCategory> data = eventCategoryRepository.findById(eventCategoryId);
		return (data.isPresent()) ? new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), data.get())
				: new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage());
	}

	@Override
	public ResponseData getEventCategoryByName(String eventCategoryName) {
		List<com.psp.model.EventCategory> eventCategory = eventCategoryRepository
				.findByEventCategoryName(eventCategoryName);
		return (eventCategory.isEmpty()) ? new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage())
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventCategory);
	}

	@Override
	public ResponseData getAllEventCategory() {
		List<com.psp.model.EventCategory> allEventCategory = eventCategoryRepository.findAll();
		return (allEventCategory.isEmpty())
				? new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage())
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), allEventCategory);
	}

	@Override
	public ResponseData deleteEventCategoryById(Long id) {
		eventCategoryRepository.deleteById(id);
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(),
				"Event-category has been deleted successfully");
	}

	@Override
	public ResponseData deleteAllEventCategory() {
		eventCategoryRepository.deleteAll();
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(),
				"All event-category has been deleted successfully");
	}

	@Override
	public ResponseData getEventByCategoryId(Long categoryId) {
		List<Events> eventList = eventRepository.findByEventCategory_EventCategoryId(categoryId);
		return (eventList.isEmpty()) ? new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage())
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventList);
	}

	@Override
	public ResponseData totalEventCount() {
		List<Events> eventList = eventRepository.findAll();
		return (eventList.isEmpty()) ? new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage())
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventList.size());
	}

	@Override
	public Integer getTotalSeatByEventId(Long id) {
		return eventRepository.getTotalSeatByEventId(id);
	}
}
