import { Typography } from "antd";
import CodeForm from "./codeForm";
import CodeList from "./codeList";

const { Title } = Typography;

export default function CodeContainer() {
  return (
    <div>
      <Title>Code</Title>
      <CodeForm code={{ id: 0, name: "", codeTypeId: null }} />
      <CodeList />
    </div>
  );
}
