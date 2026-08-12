package com.internship.helpdesk.repository;

import com.internship.helpdesk.entity.Ticket;
import com.internship.helpdesk.entity.User;
import com.internship.helpdesk.enums.TicketCategory;
import com.internship.helpdesk.enums.TicketPriority;
import com.internship.helpdesk.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketNumber(String ticketNumber);

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByPriority(TicketPriority priority);

    List<Ticket> findByCategory(TicketCategory category);

    List<Ticket> findByCreatedBy(User user);
}