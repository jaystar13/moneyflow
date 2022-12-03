import { Typography } from "antd";
import AccountForm from "./accountForm";
import AccountList from "./accountList";

const { Title } = Typography;

export default function AccountContainer() {
  return (
    <div>
      <Title>계좌관리</Title>
      <AccountForm />
      <AccountList />
    </div>
  );
}
