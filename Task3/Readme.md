# 💬 Java TCP Chat Application

A simple **multi-client chat application** built using Java TCP sockets. This project demonstrates real-time communication between multiple clients via a central server using Java’s socket programming and multi-threading.

---

## 📁 Project Structure

| File Name         | Description                                                    |
|-------------------|----------------------------------------------------------------|
| `ChatServer.java` | Accepts multiple client connections and handles message broadcasting |
| `ChatClient.java` | Connects to the server, sends messages, and receives messages |

---

## ✅ Features

- Supports multiple clients
- Real-time message broadcasting to all clients
- Terminal-based interface
- Built using `Socket`, `ServerSocket`, `Thread`, `BufferedReader`, and `PrintWriter`

---

## 🔧 Requirements

- Java JDK 8 or later
- Terminal or Command Prompt
- Optional: IDE like IntelliJ, Eclipse, or VS Code

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/java-tcp-chat.git
cd java-tcp-chat
```

### 2. Compile the Source Files

```bash
javac ChatServer.java
javac ChatClient.java
```

### 3. Run the Server

```bash
java ChatServer
```

Expected output:

```
Server started. Waiting for clients...
```

### 4. Run One or More Clients (in different terminals)

```bash
java ChatClient
```

Expected output:

```
Connected to chat server. Type messages below:
```

Now start typing and chatting between multiple terminals!

---

## 🔁 How It Works

1. The server listens on port `12345` for incoming client connections.
2. When a client connects, the server spawns a new thread (`ClientHandler`) to manage communication with that client.
3. All messages received by the server are broadcasted to **all connected clients** (except the sender).

---

## 📦 Create and Run JAR Files (Optional)

If you prefer running compiled `.jar` files:

### 1. Create JARs

```bash
jar cfe ChatServer.jar ChatServer ChatServer.class
jar cfe ChatClient.jar ChatClient ChatClient.class
```

### 2. Run JARs

```bash
java -jar ChatServer.jar
java -jar ChatClient.jar
```

---

## 🔄 Customization

- Change the port number in both files if needed (default: `12345`).
- Update `"localhost"` in `ChatClient.java` if running on a remote server.
- You can also enhance the project with:
  - GUI (JavaFX/Swing)
  - Authentication
  - Message logging
  - Encrypted messaging

---

## 🌐 Resources

- [Java Sockets Tutorial (Oracle)](https://docs.oracle.com/javase/tutorial/networking/sockets/)
- [Java Concurrency Guide](https://docs.oracle.com/javase/tutorial/essential/concurrency/)

---

## 👨‍💻 Author

**Prashant Kumar Pathak**

> Feel free to fork, clone, and improve this project! Contributions are welcome.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
