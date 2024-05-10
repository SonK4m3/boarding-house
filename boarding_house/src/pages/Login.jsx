import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { LOGIN } from "../constant/ENDPOINT";

function Login() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const handleChange = (event) => {
    setUser((prev) => ({ ...prev, [event.target.name]: event.target.value }));
  };

  const handleSubmit = async () => {
    const response = await fetch(LOGIN, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });

    if (response.ok) {
      const data = await response.json();
      if (data.success) {
        sessionStorage.setItem("loggedInMember", JSON.stringify(data.result));

        if (data.result.role === "owner") navigate("home");
        else navigate("tenant-home");
      }
    } else {
      console.error("Error fetching dashboard data");
    }
  };

  return (
    <div className="w-100 text-center">
      <h1>Login</h1>
      <div
        className="justify-content-center"
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <div className="mb-3">
          <label htmlFor="exampleFormControlInput1" className="form-label">
            Tài khoản:
          </label>
          <input
            className="form-control"
            type="text"
            name="username"
            id="exampleFormControlInput1"
            value={user.username}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="exampleFormControlInput2" className="form-label">
            Mật khẩu:
          </label>
          <input
            className="form-control"
            type="password"
            name="password"
            id="exampleFormControlInput2"
            value={user.password}
            onChange={handleChange}
          />
        </div>
        <div>
          <button className="btn btn-primary" onClick={handleSubmit}>
            Đăng nhập
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;
