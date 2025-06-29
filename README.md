
# TimeServlet Project

## 📌 Description

This is a simple learning project that demonstrates the basics of using Java Servlets and Filters.

The project consists of two parts:
1. **`TimeServlet`** — a servlet that returns the current time for a given timezone.
2. **`TimezoneValidateFilter`** — a web filter that validates the `timezone` parameter and returns an error if the timezone is invalid.

---

## 🚀 How it works

### ✅ `TimeServlet`

- Handles `GET` requests at the `/time` endpoint.
- Accepts an optional query parameter `timezone` (e.g. `?timezone=UTC+2`).
- If the parameter is provided and valid, it returns the current time for the given offset.
- If the parameter is missing, it defaults to UTC.

Example request:
```
http://localhost:8080/time?timezone=UTC+2
```

Example response:
```
2022-01-05 12:05:01 UTC+2
```

---

### ✅ `TimezoneValidateFilter`

- Intercepts all requests to `/time`.
- Checks if the `timezone` parameter is present and valid.
- Only allows numeric offsets from `-12` to `+12` (inclusive).
- If the parameter is invalid or not a number, it returns:
  - **HTTP 400 Bad Request**
  - Response body: `Invalid timezone`

---

