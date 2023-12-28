# Restaurant API

## Business requirements

1. The restaurant can receive new clients any time.
2. Clients can make reservations to host special events at the restaurant.
3. The restaurant can host at most one special event per day.
4. Clients can order items from the available menu.
5. Each client order can be composed of multiple menu items.
6. Most menu items are prepared from multiple supplied ingredients.
7. The restaurant has different suppliers for the ingredients used.
8. Each ingredient has just one supplier associated with it.
9. Clients can make table reservations.
10. The restaurant provides tables of different sizes (measured by the number of seats available).

## MVP features

1. The API should allow the adding, editing and viewing of client personal information (first name, last name, email, phone number).
2. There should be an endpoint that allows the removing/adding of new client orders for the available menu items. Also, there should be one that returns the orders made by a given client.
3. The API should provide an endpoint for adding special event reservations. It should check if the specified day is already occupied or not.
4. It should be possible to add/remove items to the menu. The item's ingredients list should be specified when added.
5. The API should make available endpoints for adding/removing menu item ingredients. The ingredient's supplier should be specified when added.

