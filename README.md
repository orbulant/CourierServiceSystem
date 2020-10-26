# CourierServiceSystem
## OODJ Assignment System

*By Chen Wey Kiat And Tan Ka-Shing*


### Users
-   Customers
-	Managing staff
-	Delivery staff


### Functional Requirements

#### Managing Staff


Login/Logout
- [x]	Login
- [x]	Logout
- [x]   Recent Login


User account management
- [x]	Create accounts (Managing/Delivery)
    - UNIQUE ID, Name, NRIC, Address, Phone Number, DOB, Location assigned, Role
- [x]	Delete account
- [x]	Edit accounts
- [x]	View account


Search account
- [ ]	Search account


Order Management
- [x]	Enter new order
- [x]	Edit order
- [x]	View order
- [x]	Assign order to delivery staff
- [x]	Manually assign order
- [x]	Cancel order assignment


Feedback Management
- [x]	View Feedback


Feedback Management
- [ ]	View Feedback


Feedback Management
- [ ]	View Feedback


Delivery cancelation
- [x]	Approve delivery cancelation
- [x]	Reject delivery cancelation


Report
- [ ]	Generate report (User report, Sales report, Inventory report)


#### Delivery Staff


Login/Logout
- [x]	Login
- [x]	Logout


User account management (Individual profile management)
- <del>[ ]	Edit own account</del> Removed functionality;Reason: From a security standpoint, delivery staff changing their credentials means they can do some sketchy stuff.
- [x]	View own account


Availability
- [ ]	Set availability status
    - Check-in/Check-out
    
    
Delivery management
- [x]	View assigned orders
- [x]	Confirm order delivery
- [x]	Cancel order delivery
    - Provide reasoning details
- [x]   Enter Feedback
