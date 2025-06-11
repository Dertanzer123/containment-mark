# Prison Management System

![Java](https://img.shields.io/badge/language-Java-blue?logo=java)
![License](https://img.shields.io/github/license/Dertanzer123/containment-mark)
![Commits](https://img.shields.io/github/commit-activity/t/Dertanzer123/containment-mark)
![Last Commit](https://img.shields.io/github/last-commit/Dertanzer123/containment-mark)
![Team](https://img.shields.io/badge/team-Dertanzer123%20%7C%20TcePrepK%20%7C%20Alaattin-yellow)

### Dokuz Eylül University – Department of Computer Engineering

#### Course Project – Object-Oriented Programming

---

## 📝 Project Info

**Project Title**: Prison Management System  
**Course Instructor**: Prof. Dr. Semih Utku  
**Course Code**: CSE-2210

---

## 🧭 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [System Requirements](#-system-requirements)
- [Technologies Used](#-technologies-used)
- [Architecture](#-architecture)
- [Use Cases](#-use-cases)
- [OOP Diagrams](#-oop-diagrams)
- [Project Reports](#-project-reports)
- [License](#-license)

---

## 📖 Overview

The **Prison Management System** is a modular and scalable desktop application developed to optimize the daily
operations of prison staff.
The system focuses on centralized prisoner and staff management, cell allocation, visitor tracking, and report
generation.
**PMS** aims to increase **security**, **efficiency**, and **productivity** of prison environments by transition from
manual systems to structured, secure digital processes.

---

## 🔧 Features

- **Prisoner Management**: Register and manage detailed information about each prisoner.
- **Staff Management**: Assign and manage staff members, including their user credentials.
- **Visitor Management**: Track and manage each visit history of each prisoner.
- **Cell Allocation**: Allocate prisoners to cells and manage cell capacities.
- **Role-Based Access**: Permissions to access specific features based on the user's role.
- **Secure Authentication**: Encrypted credentials and role-sensitive access.
- **Performance-Oriented**: Optimized for performance and scalability.

---

## 💻 System Requirements

- **Java Version**: 17 or higher
- **Operating System**: Windows, Linux, or macOS

---

## 🛠 Technologies Used

- **Language**: Java
- **Encryption**: SHA-256 for password hashing
- **Version Control**: Git

---

## 🏗 Architecture

- **Modular Design**: Each functionality (prisoners, staff, visits, etc.) exists as an independent module for easy
  maintenance and scalability.
- **Role-Based Access Control**: Users access only what is allowed based on their role.
- **Encryption**: Passwords are hashed using SHA-256 for secure storage and transmission.

---

## 📝 Use Cases

| Use Case               | Role      | Description                                             |
|------------------------|-----------|---------------------------------------------------------|
| Staff Login            | All Staff | Login with username and password to access the system.  |
| Update Staffs          | Admin     | Add or modify staff accounts and permissions.           |
| View Prisoner Info     | Guard     | View prisoner records and logs without editing.         |
| Add New Prisoner       | Clerk     | Enter personal, legal, and cell data of a new prisoner. |
| Register Visitor       | Clerk     | Log visitors and schedule visits.                       |
| Update Cell Allocation | Clerk     | Reassign prisoners to different cells.                  |

---

## 📊 OOP Diagrams

All the diagrams can be found in the `diagrams` folder.

- [**Use Case Diagram**](./diagrams/Use%20Case%20Diagram.png)
- [**Class Diagram**](./diagrams/Class%20Diagram.png)
- [**Sequence Diagram**](./diagrams/Sequence%20Diagram.png)
- [**State Chart Diagram**](./diagrams/State%20Chart%20Diagram.png)
- [**Activity Diagram**](./diagrams/Activity%20Diagram.png)

---

## 📄 Project Reports

The project reports can be found in the `reports` folder.

- [**Analysis and Requirements Definition**](./reports/Analysis%20and%20Requirements%20Definition.pdf)
- [**System Design**](./reports/System%20Design.pdf)

---

## 📜 License

This project is licensed under the MIT License - see
the [LICENSE](https://github.com/Dertanzer123/OOP-Project/blob/main/LICENSE) file for details.