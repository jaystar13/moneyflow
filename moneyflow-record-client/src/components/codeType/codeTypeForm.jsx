import { Typography, Input, Form, Button, InputNumber } from "antd";
import { useEffect } from "react";

const { Title } = Typography;

export default function CodeTypeForm({ codeType, onUpdateCodeType, onSubmit }) {
  const [form] = Form.useForm();

  const onFinish = () => {
    onSubmit(codeType);
  };

  const onFieldsChange = (changedFields) => {
    changedFields.forEach((field) => {
      onUpdateCodeType(field.value, field.name[0]);
    });
  };

  const setCodeTypeForm = () => {
    form.setFieldsValue({ id: codeType.id, name: codeType.name });
  };

  useEffect(() => {
    setCodeTypeForm();
  }, [setCodeTypeForm]);

  return (
    <div>
      <Title level={3}>{codeType.id === 0 ? "Add" : "Modify"} Code Type</Title>
      <Form
        form={form}
        name="codetype-form"
        labelCol={{ span: 3 }}
        wrapperCol={{ span: 16 }}
        onFinish={onFinish}
        onFieldsChange={onFieldsChange}
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
