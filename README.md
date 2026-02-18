<p align="center">
<img width="600" height="300" alt="virtualcloset-logo-git" src="https://github.com/user-attachments/assets/5d2c9b85-1fca-46c3-9b58-3ac3b5c9aa55" />
</p>

<div align="center">
  
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![JavaFX](https://img.shields.io/badge/JavaFX-2D7489?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)](https://www.python.org/)
[![FastAPI](https://img.shields.io/badge/FastAPI-005571?style=for-the-badge&logo=fastapi)](https://fastapi.tiangolo.com/)
[![Google Gemini](https://img.shields.io/badge/Google%20Gemini-8E75B2?style=for-the-badge&logo=google&logoColor=white)](https://deepmind.google/technologies/gemini/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
</div>

## 👗 Overview
VirtualCloset is your personalized **AI-powered wardrobe assistant**! The assistant helps users organize their clothes, generate outfit ideas, and discover new combinations using Google Gemini, JavaFX and Python.

---

## ✨ Features
VirtualCloset offers a variety of functions to help the user manage the closet, including:
- **Item Management** - Add, edit, and remove items in a **digital closet**.
- **ChatBot Assistent** - Ask in natural language (e.g., “I have a red floral skirt”) and get **AI outfit ideas**.
- **Dress Critique** - Pair selected items and get a **Gemini-powered** rationale.
- **Real-time Data Modification** - Data persisted locally via **SQLite**.

---

## 🧠 Inspiration
Deciding what to wear is time-consuming. Many people struggle to decide what to wear or simply don’t have the time to design stylish outfits. We were inspired by this everyday challenge and wanted to make fashion smarter and more effortless by combining creativity with AI. 

---

## 📦 Tech Stack
- **Frontend:** Java, JavaFX
- **Backend:** Python, FastAPI
- **AI:** Google Gemini (via `google-genai`)
- **Data:** SQLite + Pandas
- **Other:** Pydantic, SQLAlchemy, Uvicorn, python-dotenv


---
## 💻 Installation
### ⚙️ Prerequisites
- **Python 3.11+**
- **Java 17+** (JDK)
- **Maven** (for the JavaFX app)
- A Google Gemini API key



### 🔐 Environment Variables
Create a `.env` file inside **`clothe-ai-backend/`**:
- **GOOGLE_API_KEY = your-real-key**
> Tip: Do **not** hardcode keys in code or commit `.env`.

---

## 🚀 Run the Backend (FastAPI)
```bash
cd clothe-ai-backend
pip install -r requirements.txt

# start dev server:
uvicorn app:app --reload
# Server: http://127.0.0.1:8000
# Docs:   http://127.0.0.1:8000/docs

# run the front end:
cd clothe-ai-frontend/main_page
mvn clean javafx:run

# in your java code, point API front end to:
http://127.0.0.1:8000
