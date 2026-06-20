
Feature:Submit Order on ECommerce Store

Background:
Given i landed on ecommerce home page


Scenario Outline:
Submit the Order happy path
Given  Logged in with <email> and <password>
When I add product <productName> to cart
And checkout <productName> and submit the order
Then "Thankyou for the order." message will be displayed

Examples:
|email				|password	|productName		|
|arijit@kayal.com	|Arijit@123	|ZARA COAT 3		|
|lina@kayal.com		|Lina@123	|ADIDAS ORIGINAL	|
