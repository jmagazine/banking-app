"use client";
import "./globals.css";
import "./page.css";
import "../api/users/userService";
import { User } from "../api/users/user";
import { useRouter } from "next/navigation";
import { ChangeEvent, use, useRef, useState, useEffect } from "react";
import findUserWithCredentials from "../api/users/userService";
import getAccountsOwnedByUser from "@/api/accounts/accountService";

interface KeyboardEvent {
  key: string;
}

export default function HomePage() {
  // TODO: make it so that username variable is updated if it is autofilled
  const router = useRouter();
  const [isLogin, setIsLogin] = useState(true);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [usernameTakenError, setUsernameTakenError] = useState("");
  const [passwordMismatchError, setPasswordMismatchError] = useState("");
  const usernameInputRef = useRef<HTMLInputElement>(null);
  const passwordInputRef = useRef<HTMLInputElement>(null);
  const confirmPasswordInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (isLogin && usernameInputRef.current) {
      setUsername(usernameInputRef.current.value);
    }
    if (isLogin && passwordInputRef.current) {
      setPassword(passwordInputRef.current.value);
    }
  }, []);

  useEffect(() => {
    const handleKeyDown = (e: any) => {
      if (e.key === "Enter") {
        e.preventDefault(); // Prevent default form submission
        handleLogin(e as any); // Trigger login
      }
    };

    // Attach event listener
    window.addEventListener("keydown", handleKeyDown);

    // Clean up event listener on unmount
    return () => {
      window.removeEventListener("keydown", handleKeyDown);
    };
  }, [username, password]);

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

  const handleUsernameKeyDown = (e: any): void => {
    if (e.key === "Enter") {
      e.preventDefault();
      passwordInputRef.current?.focus();
    }
  };
  const handlePasswordKeyDown = (e: any): void => {
    if (e.key === "Enter") {
      e.preventDefault();
      confirmPasswordInputRef.current?.focus();
    }
  };
  const handleConfirmPasswordKeyDown = (e: any): void => {
    if (e.key === "Enter") {
      e.preventDefault();
    }
  };

  // Used for pressing enter when forms are already populated
  const handleFormKeyDown = (e: any) => {
    if (e.key === "Enter") {
      e.preventDefault();
      handleLogin(e as any);
    }
  };

  const handleLogin = async (
    e: React.MouseEvent<HTMLButtonElement>,
  ): Promise<void> => {
    e.preventDefault();
    if (isLogin) {
      let userOrError = await findUserWithCredentials({
        username: username,
        password: password,
      });
      console.log(userOrError);
      if (userOrError === undefined) {
        console.error(
          `failed to find user with username: ${username} and password ${password}. `,
        );
      }
      if (!userOrError.error) {
        const user: User = userOrError;
        const ownerId = user["id"];
        // navigate to /home with prop {ownerId: ownerId}
        sessionStorage.setItem("userId", ownerId.toString());
        router.push(`/dashboard`);
      } else {
        console.log(`Failed to log in: ${userOrError.error}`);
      }
    }
  };

  function LoginPage() {
    return (
      <div className="bg-sky-100 flex justify-center items-center h-screen">
        <div className=" left-image w-1/2 h-screen hidden lg:block">
          <img
            src="https://img.freepik.com/fotos-premium/imagen-fondo_910766-187.jpg?w=826"
            alt="Placeholder Image"
            className="object-cover w-full h-full"
          />
        </div>
        <div className="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
          <h1 className="text-2xl font-semibold mb-4">Login</h1>
          <form action="#" method="POST">
            <div className="mb-4 bg-sky-100">
              <label htmlFor="username" className="block text-gray-600">
                Username
              </label>
              <input
                type="text"
                id="username"
                name="username"
                className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                autoComplete="on"
                onKeyDown={handleUsernameKeyDown}
                ref={usernameInputRef}
              />
            </div>
            <div className="mb-4 password-input">
              <label htmlFor="password" className="block text-gray-800">
                Password
              </label>
              <input
                type="password"
                id="password"
                name="password"
                className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                autoComplete="on"
                ref={passwordInputRef}
              />
            </div>
            <div className="mb-4 flex items-center remember-me-checkbox-container">
              <input
                type="checkbox"
                id="remember"
                name="remember"
                className="text-red-500"
              />
              <label htmlFor="remember" className="text-green-900 ml-2">
                Remember Me
              </label>
            </div>
            <div className="mb-6 text-blue-500">
              <a href="#" className="hover:underline">
                Forgot Password?
              </a>
            </div>
            <button
              type="submit"
              className="bg-red-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full"
              onClick={handleLogin}
              onKeyDown={handleFormKeyDown}
            >
              Login
            </button>
          </form>
          {isLogin && (
            <div className="mt-6 text-green-500 text-center">
              <a
                href="#"
                className="hover:underline"
                onClick={() => setIsLogin(false)}
              >
                Sign up Here
              </a>
            </div>
          )}
        </div>
      </div>
    );
  }

  function SignupPage() {
    return (
      <div className="bg-sky-100 flex justify-center items-center h-screen">
        <div className=" left-image w-1/2 h-screen hidden lg:block">
          <img
            src="https://img.freepik.com/fotos-premium/imagen-fondo_910766-187.jpg?w=826"
            alt="Placeholder Image"
            className="object-cover w-full h-full"
          />
        </div>
        <div className="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2">
          <h1 className="text-2xl font-semibold mb-4">Login</h1>
          <form action="#" method="POST">
            <div className="mb-4 bg-sky-100">
              <label htmlFor="username" className="block text-gray-600">
                Username
              </label>
              <input
                type="text"
                id="username"
                name="username"
                className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                autoComplete="off"
                onKeyDown={handleUsernameKeyDown}
                ref={usernameInputRef}
              />
            </div>
            <div className="mb-4 password-input">
              <label htmlFor="password" className="block text-gray-800">
                Password
              </label>
              <input
                type="password"
                id="password"
                name="password"
                className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                autoComplete="off"
                ref={passwordInputRef}
              />
            </div>
            <div className="mb-4 confirm-password-input">
              <label htmlFor="confirm-password" className="block text-gray-800">
                Confirm Password
              </label>
              <input
                type="password"
                id="confirm-password-input"
                name="confirm password"
                className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                autoComplete="off"
                onKeyDown={handleConfirmPasswordKeyDown}
                ref={confirmPasswordInputRef}
              />
            </div>
            <div className="mb-4 flex items-center remember-me-checkbox-container">
              <input
                type="checkbox"
                id="remember"
                name="remember"
                className="text-red-500"
              />
              <label htmlFor="remember" className="text-green-900 ml-2">
                Remember Me
              </label>
            </div>
            <button
              type="submit"
              className="bg-red-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full"
              onClick={handleLogin}
              onKeyDown={handleFormKeyDown}
            >
              Login
            </button>
          </form>

          <div className="mt-6 text-green-500 text-center">
            <a
              href="#"
              className="hover:underline"
              onClick={() => setIsLogin(true)}
            >
              Login Here
            </a>
          </div>
        </div>
      </div>
    );
  }

  return isLogin ? LoginPage() : SignupPage();
}
