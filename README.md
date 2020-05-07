# library
Library to share books and get real time information about the new inserted books and users categories subscription, this app was developed with spring boot, websoockets and stomp
<h3>Virtual Book Story</h1>
<p>The main idea is to provide options to share books belonging to different categories, also provides the option to subscribe to a category and receive real-time information about books belonging to the selected category, the user subscribed to a category can see statistics in time actual subscriptions of other users to all the book categories managed by the system</p>
<img src="/opt/libraryFront1.png"/>
<h5>Category subscription interface</h5>
<img src="/opt/subscription1.png"/>
<h5>Insertion book form interface</h5>
<img src="/opt/bookForm1.png"/>
<h5>Book details interface</h5>
<img src="/opt/bookDetails1.png"/>
<h5>Books list interface</h5>
<img src="/opt/booksList1.png"/>
<h5>Dependencies</h5>
<ul>
  <li>flyway-core</li>
  <li>com.h2database</li>
  <li>spring-boot-starter-websocket</li>
  <li>stomp-websocket</li>
   <li>sockjs-client</li>
  <li>webjars-locator-core</li>
   <li>bootstrap</li>
  <li>jquery</li>
   <li>spring-boot-starter-thymeleaf</li>
</ul>
<p>the files related to each book (cover and document) are stored in the "upload-dir" folder, this folder is automatically created by the application</p>
