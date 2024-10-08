# LocateLot

Parking Searching App with Filtering and Review Features

Team Name: API Alchemists

Domain:
The domain for our project is smart parking solutions. 
The purpose of this domain deals with optimizing the search for parking spaces 
within local areas by integrating location services and real-time data to 
enhance user experience and efficiency.

INDEX 
- Software Specification
- User Stories
- Proposed Entities
- Schedule Planning
- Directions
- Functionality


SOFTWARE SPECIFICATION
The program allows users to locate parking spaces closest to their current location or a specified address. The results can be filtered based on proximity, cost, or availability. The user can also access information about the parking space including location and address, type (garage, surface), pricing, availability (whether it is open), a link to the parking company’s website (if applicable), and reviews regarding ease of entry and locating the space. The program will guide the users to the selected space or redirect them to another platform that does (Google Maps, Apple Maps). The program can also be extended in the future to locate/display other features such as washrooms and drinking water access.


USER STORIES
1.	Jamie is driving to a new coffee shop and wants to find a nearby parking space. They use the proximity filter and specify a search radius of 1 km to find the nearest parking spots to the coffee shop. The app displays all the parking spots within the radius and shows the spot’s features and reviews for the user to see when selected. [Team’s story]

2.	Alice is planning on spending her entire day at the shopping mall, so she is trying to minimize any additional costs to her day. She inputs her location and selects sort by pricing to find the cheapest parking spot near her. [Parmis’s story]

3.	Sara is attending a show in downtown Toronto but is stuck in rush hour. She realizes there’s no available street parking near the theater. She needs to find the nearest parking lot with available spaces and get there in the quickest time possible. [Asma’s story]

4.	Joe wants to park his vehicle near a rest stop but has previously incurred significant costs in damages from low-hanging banner boards scratching the top of his truck. He peruses the “ease of entry” reviews to determine the safest and most convenient parking choice for him [Gouri’s story]

5.	Bob the Builder is running late and struggling to find a nearby parking space that is not occupied. He selects the proximity and availability filters, and specifies a 3km radius for his search request to find available spaces nearby. [Nikoo’s story]

6.	Thomas Shelby is looking for an underground parking facility for his new Bentley, and uses the app to filter by type of parking lot, searching only for “Garage” type lots. He sorts by the type of parking lot and finds the closest one. [Arnav’s story]

7.	Ben is in a rush and wants to know if he can find a spot in the parking lots nearby by checking the reviews in the app. After driving to a parking lot, he realizes that it is easier to find a spot than what is reported by the app. So, he left a review to help with the app's accuracy. [Fata’s story]


PROPOSED ENTITIES

-	ParkingSpot
     Array<float> latitudeLongitude
     String streetAddress
     String linkToWebsite
     Array<Review> reviews
     HashMap<String, String> timesToRates

-	Displayer
     ArrayList<ParkingSpot> listOfParkingSpots

-	Filter
     int proximity
     boolean availability
     float pricing
     float searchRadius
 	Str carparkType

-	Review
     float easeOfEntry (expressed as a star rating)
     float easeOfFinding (expressed as a star rating)


SCHEDULE PLANNING
Scheduled Meeting Times + Mode of Communication:

Meeting time outside of lab- Tuesdays 5pm-6pm, Weekends
Mode of Communication- Instagram group chat, Discord call 

DIRECTIONS TO SEARCH :
1. Set up environmental variable (google maps) - API key
2. Add and download the library *com.fasterxml.jackson.core:jackson-annotations:2.13.0* to Project Structure and Apply 
3. Run GUI.java 
4. Type and select street address from the drop-down list - ensure the typed address matches the selection (will not work otherwise -- see below for example inputs)
5. Select the desired filter/sorting method from the buttons 
   - Proximity - sorts the nearest parking lots from nearest to furthest
   - Radius - edit the radius of your search to view parking lots within a certain distance (currently set at default 3km)
   - Price - view the nearest parking lots from least to most expensive
   - Ease of entry - implemented - view the 5 nearest parking lots from most to least convenient entrances (does not sort currently as review storing functionality has not yet been tested; entity.EOEFilter contains sorting logic and can be tested in test.unit_tests.EOEFilterTest)
   - Availability - view the nearest parking lots from largest to smallest capacity
   - Type - filter your search for parking lots based on type (garage, surface)
6. View the list of ideal parking lots at the bottom of the console ("Run" tab on the left on IntelliJ)

EXAMPLE INPUTS : (please type out in full **and** select corresponding option)
- 81 Bay Street, Toronto, ON, Canada
- 255 McCaul Street, Toronto, ON, Canada
- 40 St George Street, Toronto, ON, Canada
- 27 King's College Circle, Toronto, ON, Canada
- 1 Spadina Crescent, Spadina Crescent, Toronto, ON, Canada


DIRECTIONS TO SUBMIT A REVIEW :
- Make sure to have the _com.fasterxml.jackson.core_ dependency in your project structure.
- As soon as the GUI runs, the console will ask for a review input. Input the id of the desired parking lot and the rating as well, both as integers. It will update the Reviews.json file and report if it was successful in the console. An invalid input will stop the program from asking for a review.

FUNCTIONALITY

Present
* street address search autocomplete 
* ease of entry sorting
* tests for entity.ParkingLot, entity.EOEFilter, EOE(Presenter, InputData) OutputData, FakeGUI(to test presenter), SubmitReview(InputData, OutputData, Presenter)
* radius filter
* proximity sorting
* availability filter
* type of carpark: surface or garage

In Progress
* type filter 
* price sorting 
