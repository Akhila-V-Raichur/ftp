# File Transfer Protocol (FTP) Java Project

## Overview
This project demonstrates a simple FTP (File Transfer Protocol) system using Java. It enables the transfer of files between a client and a server over a TCP connection. The client uploads files to the server, and the server receives and saves them locally.

## Key Features
- Server continuously listens for incoming connections from clients.
- Clients can connect to the server and send files.
- Supports file metadata exchange such as file name and size.
- File transfer is done using a buffer system for efficient data handling.
  
## How It Works
1. **Server:** 
   - Starts and listens on a specified port (e.g., 5555).
   - Accepts client connections and waits for file transfer requests.
   - Receives files from the client, saves them to the local disk, and confirms the successful transfer.

2. **Client:** 
   - Connects to the server using the specified server address and port.
   - Sends a file to the server, including the file name and size.
   - Transfers file data in chunks, using a buffer for performance optimization.

## Dataset
No specific dataset is required, but you will need a file to test the client-server communication.

## Usage Instructions

### Server
1. Run the `FileTransferProtocolServer.java` file.
2. The server will wait for client connections on the specified port (e.g., 5555).

### Client
1. Run the `FileTransferProtocolClient.java` file.
2. Provide the file path you want to send to the server.
3. The client will connect to the server and upload the specified file.
