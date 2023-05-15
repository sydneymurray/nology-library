# nology-carpark

## About
A JAVA console Library application that members can check in and checks out books.  \
Administrator can also see the status of lent books.\

## Configuration
The application will save its state in the following JSON files:- \
\src\main\resources\books_data.json \
\src\main\resources\member_data.json 

The admin_password.txt file is used to upgrade a member to an administrator. 

## Start the app
Initial run, please ensure \src\main\resources\books_data.csv file is present \
The application will automajically create a books_data.sjon file.

Open the folder using IntelliJ and run the main method \

## Admin Functions
Administrators can produce reports on lent books and how many times they have been lent
The reports can be found here:-
\src\main\resources\loaned)books.csv \
\src\main\resources\lent_books.csv 

