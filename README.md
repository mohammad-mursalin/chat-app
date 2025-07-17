# 💬 Real-Time Chat Application

A full-stack real-time chat application built with **Spring Boot (Java)** and **Vanilla JavaScript** that supports:

- ✅ One-on-one and group chat
- ✅ Real-time user online/offline presence
- ✅ Message sending via WebSocket (STOMP over SockJS)
- ✅ Group creation with custom group names
- ✅ "Forgot Password" feature via email verification
- ✅ JWT (Planned) authentication
- ✅ Responsive, modern UI

---

## 🚀 Features

- 🧑‍🤝‍🧑 **Private & Group Messaging**  
  Supports both one-on-one chats and group chats with real-time updates.

- 📶 **Live User Presence Indicator**  
  Shows a green or gray badge next to each user indicating online/offline status without page refresh.

- ✍️ **Send Message via Enter Key or Send Button**  
  UX-focused design for faster messaging.

- ✉️ **Forgot Password Email Flow**  
  Users can reset their password securely via email verification.

- 🎨 **Modern UI**  
  - Chat bubbles auto-wrap long messages.
  - Maximum width set for messages (45%) to ensure readability.
  - Linear background gradient and responsive layout.

- 🌐 **Single Page Chatroom (SPA-like)**  
  Combined previous homepage and private chat into a single dynamic `chatroom.html` to simplify navigation.

---

## 🛠️ Tech Stack

| Frontend       | Backend         | Communication | Database | Misc        |
|----------------|-----------------|---------------|----------|-------------|
| HTML, CSS, JS  | Spring Boot 3.5 | WebSocket     | MongoDB  | Thymeleaf   |
| Bootstrap      | Spring Security | STOMP + SockJS|          | JavaMail API|

---

## 📸 Screenshots

| Chat Interface | Group Creation |
|----------------|----------------|
|  comming soon  |  comming soon  |


---

## 🧪 Setup & Run Locally

### Prerequisites
- Java 21
- springboot 3.5
- Maven
- MongoDB running locally (`mongodb://localhost:27017`)
- Mail configuration (Gmail SMTP or other)

### Clone & Build

```bash
git clone https://github.com/mohammad-mursalin/chat-app.git
cd realtime-chat-app
mvn clean install

Run
mvn spring-boot:run
Then open http://localhost:8080/login in your browser.

📦 Folder Structure (Important Files)

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.yourname.chatapp
│   │   │       ├── controller
│   │   │       ├── service
│   │   │       ├── model
│   │   │       ├── websocket
│   │   │       └── config
│   │   └── resources
│   │       ├── templates (Thymeleaf HTML)
│   │       └── static (JS, CSS)


🔒 Security
    Currently form-based authentication.

    JWT Authentication (Planned, In Progress).

    User details and roles stored in MongoDB.

    Passwords hashed using BCrypt.

    Email-based password recovery implemented.


✅ Upcoming Features

  🔐 JWT Token-based authentication

  👤 User Profile view & edit

  🖼️ Profile picture upload

  🔔 Push notifications

  🗑️ Delete/Edit message functionality

  📅 Last seen time

  🧠 Learnings & Purpose

This project was built to dive deep into:

  Real-time WebSocket communication using STOMP over SockJS.

  Backend architecture with Spring Boot & MongoDB.

  Frontend DOM manipulation with vanilla JS.

  Authentication flows and session handling.

  Building clean, responsive UIs.


🙋‍♂️ Author
  Mursalin Lamon
  🎓 ICE, Pabna University of Science & Technology
  💼 Junior Software Engineer @ Shefra
  🧠 Passionate about Java Backend Development

📫 LinkedIn :https://www.linkedin.com/in/mohammad-mursalin-67326a287/ | 📧 mursalinlamon@email.com

⭐ Show your support
If you like this project, please ⭐ star the repo and feel free to fork and contribute!

