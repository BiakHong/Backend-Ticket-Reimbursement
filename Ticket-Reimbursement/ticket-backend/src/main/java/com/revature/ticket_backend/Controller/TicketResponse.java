package com.revature.ticket_backend.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketResponse {
    private Long ticketId;
    private BigDecimal amount;
    private String description;
    private String status;
    private LocalDateTime submittedAt;
    private String submittedBy; // User's username

    // Constructor
    public TicketResponse(Long ticketId, BigDecimal amount, String description, String status, 
                          LocalDateTime submittedAt, String submittedBy) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.submittedAt = submittedAt;
        this.submittedBy = submittedBy;
    }

    // Getters and Setters
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
}
