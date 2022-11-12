import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import "./index.css";
import "antd/dist/antd.css";
import router from "./routes/routes";

ReactDOM.createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
