import React from "react";

import Student from "./components/Student";

const App = () => {
  return (
    <div>
      <Student
        name="Advik"
        branch="CSE-DS"
        rollno="2200320100001"
      />

      <Student
        name="Rahul"
        branch="CSE"
        rollno="2200320100002"
      />

      <Student
        name="Riya"
        branch="IT"
        rollno="2200320100003"
      />

      <Student
        name="Aman"
        branch="ECE"
        rollno="2200320100004"
      />

      <Student
        name="Priya"
        branch="AI"
        rollno="2200320100005"
      />
    </div>
  );
};

export default App;