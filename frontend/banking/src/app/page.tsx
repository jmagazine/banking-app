"use client";
import "bootstrap/dist/css/bootstrap.css";
import "./globals.css";
import "./page.css";
import "../services/apiService";
import { User } from "../services/user";
import {
  ChangeEvent,
  use,
  useRef,
  useState,
  KeyboardEvent,
  useEffect,
} from "react";
import findUserWithCredentials from "../services/apiService";
export default function HomePage() {
  const [isLogin, setIsLogin] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [usernameTakenError, setUsernameTakenError] = useState("");
  const [passwordMismatchError, setPasswordMismatchError] = useState("");

  const usernameInputRef = useRef<HTMLInputElement>(null);
  const passwordInputRef = useRef<HTMLInputElement>(null);
  const confirmPasswordInputRef = useRef<HTMLInputElement>(null);

  const checkPassword = (pass: string, confirmPass: string) => {
    if (pass !== "" && confirmPass !== "") {
      if (pass !== confirmPass) {
        setPasswordMismatchError("Passwords do not match");
      } else {
        setPasswordMismatchError("");
      }
    } else {
      setPasswordMismatchError("");
    }
  };

  const handleUsernameChange = (e: any) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
    const newPassword = e.target.value;
    setPassword(newPassword);
    checkPassword(newPassword, confirmPassword);
  };
  function handleConfirmPasswordChange(e: ChangeEvent<HTMLInputElement>): void {
    e.preventDefault();
    const newConfirmPassword = e.target.value;
    setConfirmPassword(newConfirmPassword);
    setConfirmPassword(e.target.value);
    checkPassword(password, newConfirmPassword);
  }

  const handleUsernameKeyDown = (e: KeyboardEvent<HTMLInputElement>): void => {
    if (e.key === "Enter") {
      e.preventDefault();
      passwordInputRef.current?.focus();
    }
  };
  const handlePasswordKeyDown = (e: KeyboardEvent<HTMLInputElement>): void => {
    if (e.key === "Enter") {
      e.preventDefault();
      confirmPasswordInputRef.current?.focus();
    }
  };
  const handleConfirmPasswordKeyDown = (
    e: KeyboardEvent<HTMLInputElement>,
  ): void => {
    if (e.key === "Enter") {
      e.preventDefault();
    }
  };

  const handleLogin = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();
    if (isLogin) {
      findUserWithCredentials({
        username: username,
        password: password,
      })
        .then((res) => {
          if (!res.error) {
            console.log(`User: ${JSON.stringify(res)}`);
          } else {
            console.log(`Failed to log in: ${res.message}`);
          }
        })
        .catch((err) => console.log(err));
    }
  };

  return (
    <>
      <div className="login-page-container d-flex flex-column justify-content-center mt-3 p-2">
        <div className="login-page-title d-flex flex-column align-items-center p-3">
          <h4 className="fs-1 fw-bold mb-2">Bank Management System</h4>
          <h2 className="fs-2 fst-italic mb-2">
            A bank management system with simulated portfolio behavior.
          </h2>
        </div>
        <div
          className="form-container d-flex justify-content-center align-items-center"
          style={{ backgroundColor: "transparent" }}
        >
          <form>
            <div className="mb-3">
              <label htmlFor="inputUsername" className="form-label">
                Username
              </label>
              <input
                type="text"
                className="form-control"
                id="inputUsername"
                aria-describedby="usernameHelp"
                onChange={handleUsernameChange}
                onKeyDown={handleUsernameKeyDown}
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
                onChange={handlePasswordChange}
                onKeyDown={handlePasswordKeyDown}
              />
              {passwordMismatchError !== "" && (
                <div className="passwordMismatchError text-danger">
                  {passwordMismatchError}
                </div>
              )}
            </div>
            {!isLogin && (
              <div className="mb-3">
                <label htmlFor="inputConfirmPassword" className="form-label">
                  Confirm Password
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="inputConfirmPassword"
                  onChange={handleConfirmPasswordChange}
                  onKeyDown={handleConfirmPasswordKeyDown}
                />
              </div>
            )}
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
                onClick={(e) => {
                  e.preventDefault();
                  handleLogin(e);
                }}
              >
                {isLogin ? "Log in" : "Sign up"}
              </button>
              <button
                type="submit"
                className="btn btn-secondary text-center"
                style={{ height: "40px" }}
                onClick={(e) => {
                  e.preventDefault();
                  setIsLogin(!isLogin);
                }}
              >
                {isLogin
                  ? "Don't have an account? Sign up."
                  : "Already have an account? Log in"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}
