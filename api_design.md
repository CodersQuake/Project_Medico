
## MEDICO - HOSPITAL PATIENT APPOINTMENT SYSTEM...
### Om Chaudhari - EDIT 01


---

## **API**

### **1. User Registration**

- **Endpoint:** `POST /api/auth/register`
- **Description:** Registerying a new user with role-based access.

```java
@PostMapping("/register")
public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
    userService.register(userDto);
    return ResponseEntity.ok("User registered successfully.");
}
```

### **2. User Login**

- **Endpoint:** `POST /api/auth/login`
- **Description:** Authenticates a user and returns a JWT token.

```java
@PostMapping("/login")
public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
    String token = authService.login(loginRequest);
    return ResponseEntity.ok(new AuthResponse(token));
}
```

### **3. Book an Appointment**

- **Endpoint:** `POST /api/patient/appointments`
- **Description:** Allows a patient to book an appvointment with a doctor.

```java
@PostMapping("/appointments")
public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
    appointmentService.book(appointmentDto);
    return ResponseEntity.ok("Appointment booked successfully.");
}
```

### **4. Cancel an Appointment**

- **Endpoint:** `DELETE /api/patient/appointments/{appointmentId}`
- **Description:** Cancels an existing appointment.

```java
@DeleteMapping("/appointments/{id}")
public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
    appointmentService.cancel(id);
    return ResponseEntity.ok("Appointment canceled successfully.");
}
```

### **5. Add a Doctor**

- **Endpoint:** `POST /api/admin/doctors`
- **Description:** Adds a new doctor to the system.

```java
@PostMapping("/doctors")
public ResponseEntity<String> addDoctor(@RequestBody DoctorDto doctorDto) {
    doctorService.add(doctorDto);
    return ResponseEntity.ok("Doctor added successfully.");
}
```

### **6. View All Doctors**

- **Endpoint:** `GET /api/admin/doctors`
- **Description:** Retrieves a list of all doctors.

```java
@GetMapping("/doctors")
public ResponseEntity<List<DoctorDto>> getAllDoctors() {
    List<DoctorDto> doctors = doctorService.getAll();
    return ResponseEntity.ok(doctors);
}
```

### **7. Update Appointment Status**

- **Endpoint:** `PUT /api/doctor/appointments/{appointmentId}`
- **Description:** Updates the status of an appointment.

```java
@PutMapping("/appointments/{id}")
public ResponseEntity<String> updateAppointmentStatus(@PathVariable Long id, @RequestBody AppointmentStatusDto statusDto) {
    appointmentService.updateStatus(id, statusDto);
    return ResponseEntity.ok("Appointment status updated successfully.");
}
```

---
