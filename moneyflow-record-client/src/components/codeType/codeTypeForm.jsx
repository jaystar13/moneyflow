import { Input, Form, Button, InputNumber } from "antd";

export default function CodeTypeForm({
  codeType,
  form,
  onUpdateCodeType,
  onSubmit,
}) {
  const onFinish = () => {
    onSubmit(codeType);
  };

  const onFieldsChange = (changedFields) => {
    changedFields.forEach((field) => {
      onUpdateCodeType(field.value, field.name[0]);
    });
  };

  return (
    <div>
      <h1>
        {codeType.id}
        {codeType.id === 0 ? "Add Code Type" : "Modify Code Type"}
      </h1>
      <Form
        form={form}
        name="codetype-form"
        labelCol={{ span: 3 }}
        wrapperCol={{ span: 16 }}
        onFinish={onFinish}
        onFieldsChange={onFieldsChange}
        initialValues={{ id: codeType.id, name: codeType.name }}
      >
        <Form.Item label="id" name="id" rules={[{ required: true }]}>
          <InputNumber disabled />
        </Form.Item>
        <Form.Item
          label="명칭"
          name="name"
          rules={[
            {
              required: true,
              message: "명칭을 입력하세요",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          wrapperCol={{
            offset: 3,
            span: 16,
          }}
        >
          <Button type="primary" htmlType="submit">
            Save
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}
