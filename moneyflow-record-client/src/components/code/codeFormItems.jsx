import { useEffect, useState } from "react";
import { getAllCodeTypes, createCode } from "../../api/api";

import { Typography, Form, Input, InputNumber, Button, Select } from "antd";

const { Title } = Typography;

export default function CodeForm({ code, callbackOnSubmit }) {
  const [form] = Form.useForm();

  const [options, setOptions] = useState([]);

  useEffect(() => {
    setCodeTypeSelectOptions();
  }, []);

  const setCodeTypeSelectOptions = async () => {
    const codeTypes = await getCodeTypes();
    const selectOptions = codeTypes.map((codeType) => {
      return { label: codeType.name, value: codeType.id };
    });
    setOptions(selectOptions);
  };

  const getCodeTypes = async () => {
    const res = await getAllCodeTypes();
    return res.data;
  };

  useEffect(() => {
    initCodeForm();
  }, [form]);

  const initCodeForm = () => {
    form.setFieldsValue({
      id: code.id,
      name: code.name,
      codeTypeId: code.codeType,
    });
  };

  const onFinish = (values) => {
    //console.log("onFinish", values);
    submit(values);
  };

  const submit = async (code) => {
    const res = await createCode(code);
    callbackOnSubmit();
  };

  const onFieldsChange = (changedFields) => {
    console.log("onFieldsChange");
  };

  const onChange = (value) => {
    console.log(`selected ${value}`);
  };
  const onSearch = (value) => {
    console.log("search:", value);
  };

  return (
    <div>
      <Title level={3}> {code.id === 0 ? "Add" : "Modify"} Code</Title>
      <Form
        form={form}
        name="code-form"
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
          label="코드타입"
          name="codeTypeId"
          rules={[
            {
              required: true,
              message: "코드타입을 선택하세요",
            },
          ]}
        >
          <Select
            showSearch
            placeholder="Select a code type"
            onChange={onChange}
            onSearch={onSearch}
            filterOption={(input, option) =>
              option.label.toLowerCase().includes(input.toLowerCase())
            }
            options={options}
          />
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
