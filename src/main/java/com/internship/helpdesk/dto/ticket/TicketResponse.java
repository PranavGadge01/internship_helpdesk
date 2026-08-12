package com.internship.helpdesk.dto.ticket;

import com.internship.helpdesk.enums.TicketCategory;
import com.internship.helpdesk.enums.TicketPriority;
import com.internship.helpdesk.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketResponse {

    private Long ticketId;

    private String ticketNumber;

    private String title;

    private String description;

    private TicketPriority priority;

    private TicketStatus status;

    private TicketCategory category;

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
