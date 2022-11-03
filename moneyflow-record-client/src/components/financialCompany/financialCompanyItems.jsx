import { Typography, Form, InputNumber, Input, Select } from "antd";
import { useState } from "react";
import { useEffect } from "react";
import { getCompanyTypes, getAllCodeTypes } from "../../api/api";

const { Title } = Typography;

export default function FinancialCompanyItems({ data, callback }) {
  const [companyTypeOptions, setCompanyTypeOptions] = useState([]);

  useEffect(() => {
    init();
  }, []);

  const init = () => {
    setCompanyTypeSelect();
  };

  const setCompanyTypeSelect = async () => {
    const ct = await companyTypes();

    const options = ct.map((companyType) => {
      return { label: companyType.title, value: companyType.code };
    });

    setCompanyTypeOptions(options);
  };

  const companyTypes = async () => {
    const { data: ct } = await getCompanyTypes();
    return ct;
  };

  return (
    <div>
      <Title level={3}>{data.id === 0 ? "Add" : "Modify"} 금융기관</Title>
      <Form
        name="financial-company-form"
        labelCol={{ span: 3 }}
        wrapperCol={{ span: 16 }}
      >
        <Form.Item label="id" name="id" rules={[{ required: true }]}>
          <InputNumber disabled />
        </Form.Item>
        <Form.Item
          label="명칭"
          name="name"
          rules={[{ required: true, message: "명칭을 입력하세요" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="금융기관구분"
          name="companyType"
          rules={[
            {
              required: true,
              message: "금융기관구분을 선택하세요",
            },
          ]}
        >
          <Select
            placeholder="선택하세요"
            options={companyTypeOptions}
          ></Select>
        </Form.Item>
      </Form>
    </div>
  );
}
