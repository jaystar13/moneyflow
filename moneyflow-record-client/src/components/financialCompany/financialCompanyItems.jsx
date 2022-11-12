import {
  Typography,
  Form,
  InputNumber,
  Input,
  Select,
  Button,
  Switch,
} from "antd";
import { useState } from "react";
import { useEffect } from "react";
import {
  getCompanyTypes,
  createFinancialCompany,
  updateFinancialCompany,
} from "../../api/api";

const { Title } = Typography;
const { TextArea } = Input;

export default function FinancialCompanyItems({
  data: financialCompany,
  callbackOnSave,
}) {
  const [form] = Form.useForm();

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

  useEffect(() => {
    initForm();
  }, [financialCompany]);

  const initForm = () => {
    form.setFieldsValue({ ...financialCompany });
  };

  const onFinish = (values) => {
    submit(values);
  };

  const submit = async (formData) => {
    if (formData.id === 0) {
      await asyncFunc(createFinancialCompany, formData);
    } else {
      await asyncFunc(updateFinancialCompany, formData.id, formData);
    }
    callbackOnSave(formData.id === "0" ? "save" : "update");
  };

  const asyncFunc = async (func, ...param) => {
    func(...param);
  };

  return (
    <div>
      <Title level={3}>
        {financialCompany.id === 0 ? "Add" : "Modify"} 금융기관
      </Title>
      <Form
        form={form}
        name="financial-company-form"
        labelCol={{ span: 3 }}
        wrapperCol={{ span: 16 }}
        onFinish={onFinish}
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
        <Form.Item label="사용여부" name="usable" valuePropName="checked">
          <Switch />
        </Form.Item>
        <Form.Item label="참고사항" name="definition">
          <TextArea rows={2} />
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
