# Ticket Reimbursement System

## Overview
The Ticket Reimbursement System is a web application designed to streamline the reimbursement process within a company. Employees can submit reimbursement requests, and managers can review and approve or deny them efficiently. The application is built using modern technologies and follows best practices for software development.

 ### Refer to this link for the front-end : https://github.com/BiakHong/Frontend-Ticket_Reimbursement

## Features

### Core Features
1. **Login / Register**
   - Employees and managers can log in.
   - New users can register with a default employee role.
   - Username uniqueness is validated.

2. **Submit Ticket**
   - Employees can submit reimbursement requests.
   - Tickets require an amount and a description.
   - Default ticket status is set to "Pending."

3. **Ticket Processing System**
   - Managers can view, approve, or deny pending tickets.
   - Processed tickets are removed from the pending list.
   - Ticket status is immutable after processing.

4. **View Previous Tickets**
   - Employees can view their reimbursement history.
   - Displays details of each ticket, including status.

### Extension Features (Planned)
- Reimbursement types (Travel, Lodging, Food, Other).
- User role changes by managers.
- Uploading and storing receipt images.
- User account management and profile editing.

## Technologies Used

- **Backend**: Spring Boot, Spring MVC, Spring Data
- **Frontend**: React
- **Database**: Amazon RDS
- **API**: RESTful API
- **Version Control**: GitHub
- **Testing**: Unit testing with Postman and logging frameworks

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11+
- Node.js and npm/yarn
- Postman (optional, for testing API endpoints)
- AWS RDS setup

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ticket-reimbursement-system.git
   ```
2. Navigate to the backend directory and build the application:
   ```bash
   cd backend
   mvn clean install
   ```
3. Start the Spring Boot server:
   ```bash
   mvn spring-boot:run
   ```
4. Navigate to the frontend directory and install dependencies:
   ```bash
   cd frontend
   npm install
   ```
5. Start the React application:
   ```bash
   npm start
   ```

### Running Tests
- Backend tests: Run `mvn test` in the backend directory.
- Frontend tests: Run `npm test` in the frontend directory.

## Contribution
Contributions are welcome! Please fork the repository, create a feature branch, and submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For questions or suggestions, please reach out via email or open an issue on GitHub.
