package com.internship.helpdesk.service.impl;

import com.internship.helpdesk.dto.ticket.CreateTicketRequest;
import com.internship.helpdesk.dto.ticket.TicketResponse;
import com.internship.helpdesk.dto.ticket.UpdateTicketRequest;
import com.internship.helpdesk.entity.Ticket;
import com.internship.helpdesk.entity.User;
import com.internship.helpdesk.enums.TicketStatus;
import com.internship.helpdesk.repository.TicketRepository;
import com.internship.helpdesk.security.service.CurrentUserService;
import com.internship.helpdesk.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CurrentUserService currentUserService;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            CurrentUserService currentUserService) {

        this.ticketRepository = ticketRepository;
        this.currentUserService = currentUserService;
    }

    @Override
    public TicketResponse createTicket(CreateTicketRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Ticket ticket = new Ticket();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setCategory(request.getCategory());

        ticket.setCreatedBy(currentUser);

        ticket.setStatus(TicketStatus.OPEN);

        Ticket savedTicket = ticketRepository.save(ticket);

        savedTicket.setTicketNumber(
                String.format("HD-%06d", savedTicket.getTicketId())
        );

        savedTicket = ticketRepository.save(savedTicket);

        return mapToResponse(savedTicket);
    }

    private TicketResponse mapToResponse(Ticket ticket) {

        return new TicketResponse(
                ticket.getTicketId(),
                ticket.getTicketNumber(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCategory(),
                ticket.getCreatedBy().getFirstName()
                        + " "
                        + ticket.getCreatedBy().getLastName(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }

    @Override
    public TicketResponse getTicketById(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));

        return mapToResponse(ticket);
    }

    @Override
    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TicketResponse> getMyTickets() {

        User currentUser = currentUserService.getCurrentUser();

        return ticketRepository.findByCreatedBy(currentUser)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TicketResponse updateTicket(Long ticketId, UpdateTicketRequest request) {

        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));

        existingTicket.setTitle(request.getTitle());
        existingTicket.setDescription(request.getDescription());
        existingTicket.setPriority(request.getPriority());
        existingTicket.setCategory(request.getCategory());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return mapToResponse(updatedTicket);
    }

    @Override
    public void deleteTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));

        ticketRepository.delete(ticket);
    }

}