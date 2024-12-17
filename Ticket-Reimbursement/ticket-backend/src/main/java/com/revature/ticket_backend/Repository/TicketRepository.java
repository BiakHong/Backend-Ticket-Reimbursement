package com.revature.ticket_backend.Repository;

import com.revature.ticket_backend.model.Ticket;
import com.revature.ticket_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Find tickets by user
    List<Ticket> findByUser(User user);

    // Custom query to find tickets by username using a JOIN
    List<Ticket> findByUser_Username(String username);
}