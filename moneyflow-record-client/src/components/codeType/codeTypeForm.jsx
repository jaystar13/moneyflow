import { Input, Space, Form } from "antd";

export default function CodeTypeForm({ codeType, onUpdateCodeType, onCreate }) {
  const onChange = (e) => {
    const { value, name } = e.target;
    onUpdateCodeType(value, name);
  };

  return (
    <div>
      <h1>{codeType.id === 0 ? "Add Code Type" : "Modify Code Type"}</h1>
      <Space>
        <span>명칭</span>
        <Input
          placeholder="명칭"
          type="text"
          name="name"
          value={codeType.name}
          onChange={onChange}
          style={{ width: 200 }}
        />
      </Space>
    </div>
  );
}
