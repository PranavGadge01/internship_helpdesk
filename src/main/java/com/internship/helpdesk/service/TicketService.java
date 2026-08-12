package com.internship.helpdesk.service;

import com.internship.helpdesk.dto.ticket.CreateTicketRequest;
import com.internship.helpdesk.dto.ticket.TicketResponse;
import com.internship.helpdesk.dto.ticket.UpdateTicketRequest;

import java.util.List;

public interface TicketService {

    TicketResponse createTicket(CreateTicketRequest request);

    List<TicketResponse> getAllTickets();

    TicketResponse getTicketById(Long ticketId);

    List<TicketResponse> getMyTickets();

    TicketResponse updateTicket(Long ticketId, UpdateTicketRequest request);

    void deleteTicket(Long ticketId);

}
