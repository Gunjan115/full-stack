import React, { useState } from "react";

function App() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [courses, setCourses] = useState("");

  const [list, setList] = useState([]);

  
  function handleSubmit(event) {
    event.preventDefault(); 

    if (name !== "" && email !== "" && courses !== "") {
      setList([...list, { name: name, email: email, courses: courses }]);
      setName("");
      setEmail("");
      setCourses("");
    } else {
      alert("Please fill all fields");
    }
  }

  return (
    <div>
      <h1>Fill the form</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={e => setName(e.target.value)}
        />
        <br />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
        />
        <br />
        <input
          type="text"
          placeholder="Courses"
          value={courses}
          onChange={e => setCourses(e.target.value)}
        />
        <br />
        <button type="submit">Submit</button>
      </form>
      <table border="1" style={{ marginTop: "20px" }}>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Courses</th>
          </tr>
        </thead>
        <tbody>
          {list.map((item, index) => (
            <tr key={index}>
              <td>{item.name}</td>
              <td>{item.email}</td>
              <td>{item.courses}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
