package com.revature.ticket_backend.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ticket_backend.Service.TicketService;
import com.revature.ticket_backend.Service.UserService;
import com.revature.ticket_backend.model.Ticket;
import com.revature.ticket_backend.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired 
    private UserService userService;


    // @GetMapping
    // public ResponseEntity<List<Ticket>> getAllTickets() {
    //     List<Ticket> tickets = ticketService.getAllTickets();
    //     return ResponseEntity.ok(tickets);
    // }
     @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();

        // Map the Ticket entities to TicketResponse DTOs
        List<TicketResponse> ticketResponses = tickets.stream()
                .map(ticket -> new TicketResponse(
                        ticket.getTicketId(),
                        ticket.getAmount(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getSubmittedAt(),
                        ticket.getUser().getUsername() // Fetch the username from User
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(ticketResponses);
    }

    @GetMapping("/past-tickets")
    public ResponseEntity<?> getPastTickets() {
        List<Ticket> allTickets = ticketService.getAllTickets();

        // Filter tickets where the status is not "Pending"
        List<Ticket> pastTickets = allTickets.stream()
            .filter(ticket -> ticket.getStatus() != null && !ticket.getStatus().equalsIgnoreCase("Pending"))
            .toList();

        return ResponseEntity.ok(pastTickets);
    }

    @GetMapping("/pending-tickets")
    public ResponseEntity<List<Ticket>> getPendingTickets() {
        List<Ticket> allTickets = ticketService.getAllTickets();
    
        // Filter tickets where the status is "Pending"
        List<Ticket> pendingTickets = allTickets.stream()
                .filter(ticket -> ticket.getStatus() != null && ticket.getStatus().equalsIgnoreCase("Pending"))
                .toList();
    
        return ResponseEntity.ok(pendingTickets); // Always return a list, even if empty
    }
    @PostMapping("/submit")
public ResponseEntity<?> submitTicket(@RequestBody Map<String, Object> payload) {
    String username = (String) payload.get("username");
    BigDecimal amount = new BigDecimal(payload.get("amount").toString());
    String description = (String) payload.get("description");

    // Find the user based on the username
    Optional<User> optionalUser = userService.findByUsername(username);

    if (optionalUser.isEmpty()) {
        return ResponseEntity.badRequest().body("User not found.");
    }

    User currentUser = optionalUser.get();

    // Validate required fields
    if (amount == null || description == null || amount.doubleValue() <= 0) {
        return ResponseEntity.badRequest().body("Invalid amount or description.");
    }

    // Create the ticket
    Ticket ticket = new Ticket();
    ticket.setUser(currentUser);
    ticket.setAmount(amount);
    ticket.setDescription(description);
    ticket.setStatus("Pending");
    ticket.setSubmittedAt(LocalDateTime.now());
    ticket.setUpdatedAt(LocalDateTime.now());

    // Save the ticket
    Ticket newTicket = ticketService.saveTicket(ticket);

    return ResponseEntity.status(201).body(newTicket);
}


    @PostMapping()
    public ResponseEntity<?> submitTickets(@RequestBody Ticket ticket) {
        
        if (ticket.getUser() == null || ticket.getAmount() == null || ticket.getDescription() == null){
            return ResponseEntity.badRequest().body("Fill all blank fields.");
        }
        Ticket newTicket = ticketService.saveTicket(ticket);
        return ResponseEntity.ok(newTicket);
    }
    

    

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getTicketByUser(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("User not found.");
        }
        List<Ticket> tickets = ticketService.getTicketByUser(user.get());
        return ResponseEntity.ok(tickets);
    }

    // @GetMapping("/status/{status}")
    // public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable String status){
    //     List<Ticket> tickets = ticketService.getTicketByUser(status);
    //     return ResponseEntity.ok(tickets);

    // }

    //for updating the status of ticket by Manager
    @PutMapping("/{ticketId}/update-status")
public ResponseEntity<?> updateTicketStatus(
    @PathVariable Long ticketId,
    @RequestBody Map<String, String> request
) {
    String status = request.get("status");
    Optional<Ticket> optionalTicket = ticketService.getTicketById(ticketId);

    if (optionalTicket.isEmpty()) {
        return ResponseEntity.status(404).body("Ticket not found.");
    }

    Ticket ticket = optionalTicket.get();
    ticket.setStatus(status);
    ticket.setUpdatedAt(LocalDateTime.now());
    ticketService.saveTicket(ticket);

    return ResponseEntity.ok("Ticket updated successfully.");
}




    
    
}
