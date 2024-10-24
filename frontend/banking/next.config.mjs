import { configDotenv } from "dotenv";
configDotenv({ path: "./src/api/.env.dev" });
/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  env: {
    API_BASE_URL: process.env.API_BASE_URL,
  },
};

export default nextConfig;
