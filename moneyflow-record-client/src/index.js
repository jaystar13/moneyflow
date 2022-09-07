import React from "react";
import ReactDOM from "react-dom/client";
import { Button, DatePicker, Space, version } from "antd";
import "antd/dist/antd.min.css";
import "./index.css";

const App = () => {
  return (
    <div className="App">
      <h1>antd version: {version}</h1>
      <Space>
        <DatePicker />
        <Button type="primary">Primary Button</Button>
      </Space>
    </div>
  );
};

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
