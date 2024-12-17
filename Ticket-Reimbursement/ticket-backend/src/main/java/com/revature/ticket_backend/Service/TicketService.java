package com.revature.ticket_backend.Service;


import com.revature.ticket_backend.Repository.TicketRepository;
import com.revature.ticket_backend.model.Ticket;
import com.revature.ticket_backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketByUser(User user) {
        // TODO Auto-generated method stub
        return ticketRepository.findByUser(user);
    }

    // public List<Ticket> getTicketByStatus(String status) {
    //     // TODO Auto-generated method stub
    //     return ticketRepository.findby(status);
    // }

    public Optional<Ticket> getTicketById(Long ticketId) {
        // TODO Auto-generated method stub
        return ticketRepository.findById(ticketId);
    }
}

