# CourierServiceSystem
## OODJ Assignment System

*By Chen Wey Kiat And Tan Ka-Shing*


### Users
-   Customers
-	Managing staff
-	Delivery staff


### Functional Requirements

#### Customers


Account
-	Create account
-	View account
-	Deactivate *optional*
-	Edit account


Orders
-	Give feedback


#### Managing Staff


Login/Logout
-	Login
-	Logout


User account management
-	Create accounts (Managing/Delivery)
    - UNIQUE ID, Name, NRIC, Address, Phone Number, DOB, Location assigned, Role
-	Deactivate account
-	Edit accounts
-	View account


Search account
-	Search account


Order Management
-	Enter new order
-	Edit order
-	View order
-	Assign order to delivery staff
    - Based on their geolocation area, then time.
-	Manually assign order
-	Cancel order assignment


Delivery cancelation
-	Approve delivery cancelation
-	Reject delivery cancelation


Report
-	Generate report (User report, Sales report, Inventory report)


#### Delivery Staff


Login/Logout
-	Login
-	Logout


User account management (Individual profile management)
-	Edit own account
-	View own account


Availability
-	Set availability status
    - Check-in/Check-out
    
    
Delivery management
-	View assigned orders
-	Confirm order delivery
-	Cancel order delivery
    - Provide reasoning details
