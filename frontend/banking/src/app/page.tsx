"use client";
import "bootstrap/dist/css/bootstrap.css";
import { useState } from "react";
export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  // Handle input change for username
  const handleUsernameChange = (e: any) => {
    setUsername(e.target.value);
  };

  // Handle input change for password
  const handlePasswordChange = (e: any) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e: any) => {
    return;
  };

  return (
    <>
      <div className="min-vh-100 form-container d-flex justify-content-center align-items-center bg-blue">
        <form>
          <div className="mb-3">
            <label htmlFor="inputUsername" className="form-label">
              Username
            </label>
            <input
              type="email"
              className="form-control"
              id="inputUsername"
              aria-describedby="usernameHelp"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="inputPassword" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="inputPassword"
              onChange={handleUsernameChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="inputConfirmPassword" className="form-label">
              Confirm Password
            </label>
            <input
              type="password"
              className="form-control"
              id="inputConfirmPassword"
              onChange={handlePasswordChange}
            />
          </div>
          <div className="mb-3 form-check">
            <input
              type="checkbox"
              className="form-check-input"
              id="keepMeSignedIn"
            />
            <label className="form-check-label" htmlFor="exampleCheck1">
              Keep me signed in.
            </label>
          </div>
          <div className="buttons d-flex flex-column mt-3 justify-content-evenly align-items-center">
            <button
              type="submit"
              className="btn btn-primary mb-2 d-flex text-center"
              style={{ height: "40px" }}
            >
              Sign up
            </button>
            <button
              type="submit"
              className="btn btn-secondary text-center"
              style={{ height: "40px" }}
            >
              Already have an account? Log in
            </button>
          </div>
        </form>
      </div>
    </>
  );
}
