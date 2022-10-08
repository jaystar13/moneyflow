import { Outlet, Link, Form, useNavigate } from "react-router-dom";
import {
  DesktopOutlined,
  FileOutlined,
  PieChartOutlined,
  TeamOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Breadcrumb, Layout, Menu } from "antd";
import { useState } from "react";

const { Header, Content, Footer, Sider } = Layout;

const items = [
  {
    label: "Admin",
    key: "adm1",
    icon: <PieChartOutlined />,
    children: [{ label: <Link to="codeType">Code type</Link>, key: "1" }],
  },
  { label: "Option 2", key: "2", icon: <DesktopOutlined /> },
  {
    label: "User",
    key: "sub1",
    icon: <UserOutlined />,
    children: [
      { label: "Tom", key: "3" },
      { label: "Bill", key: "4" },
      { label: "Alex", key: "5" },
    ],
  },
  {
    label: "Team",
    key: "sub2",
    icon: <TeamOutlined />,
    children: [
      { label: "Team 1", key: "6" },
      { label: "Team 2", key: "8" },
    ],
  },
];

export default function Root() {
  const [collapsed, setCollapsed] = useState(false);

  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sider
        collapsible
        collapsed={collapsed}
        onCollapse={(value) => setCollapsed(value)}
      >
        <div className="logo" />
        <Menu
          theme="dark"
          defaultSelectedKeys={["adm1"]}
          mode="inline"
          items={items}
        ></Menu>
      </Sider>
      <Layout className="site-layout">
        <Header className="site-layout-backgroud" style={{ padding: 0 }} />
        <Content style={{ margin: "0 16px" }}>
          <Breadcrumb style={{ margin: "16px 0" }}>
            <Breadcrumb.Item>Code Type</Breadcrumb.Item>
          </Breadcrumb>
          <div
            className="site-layout-background"
            style={{ padding: 24, minHeight: 660 }}
          >
            <Outlet />
          </div>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          jaystar @2022 Created by Ant UED
        </Footer>
      </Layout>
    </Layout>
  );
}
