import {
  Typography,
  Form,
  InputNumber,
  Input,
  Select,
  DatePicker,
  Button,
} from "antd";
import TextArea from "antd/lib/input/TextArea";
import moment from "moment";
import { useEffect, useState } from "react";
import { getAllFinancialCompaies, createAccount } from "../../api/api";

const { Title } = Typography;

export default function AccountItems() {
  const [form] = Form.useForm();

  const [financialCompanyCombo, setFinancialCompanyCombo] = useState([]);

  useEffect(() => {
    init();
  }, []);

  const init = () => {
    financialCompany();
  };

  const financialCompany = async () => {
    const { data } = await getAllFinancialCompaies();

    const options = data.map((item) => {
      return { label: item.name, value: item.id };
    });

    setFinancialCompanyCombo(options);
  };

  const onFinish = (values) => {
    const requestValues = {
      ...values,
      fromDate: moment(values.fromDate).format("YYYY-MM-DD"),
      toDate: moment(values.toDate).format("YYYY-MM-DD"),
    };
    saveAccount(requestValues);
  };

  const saveAccount = async (requestValues) => {
    const result = await createAccount(requestValues);
    console.log("result", result);
  };

  return (
    <div>
      <Title level={3}>계좌등록</Title>
      <Form
        form={form}
        name="account-form"
        wrapperCol={{ span: 10 }}
        onFinish={onFinish}
      >
        <Form.Item name="id" rules={[{ required: false }]}>
          <InputNumber placeholder="ID" disabled />
        </Form.Item>
        <Form.Item
          name="name"
          rules={[{ required: true, message: "명칭을 입력해주세요." }]}
        >
          <Input placeholder="명칭" />
        </Form.Item>
        <Form.Item name="financialCompanyId" rules={[{ required: true }]}>
          <Select
            placeholder="금융기관 선택"
            options={financialCompanyCombo}
          ></Select>
        </Form.Item>
        <Form.Item name="accountNo">
          <Input placeholder="계좌번호" />
        </Form.Item>
        <Form.Item name="fromDate" rules={[{ required: true }]}>
          <DatePicker placeholder="시작일자" />
        </Form.Item>
        <Form.Item name="toDate" rules={[{ required: true }]}>
          <DatePicker placeholder="종료일자" />
        </Form.Item>
        <Form.Item name="definition">
          <TextArea placeholder="설명을 입력하세요" />
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 9 }}>
          <Button type="primary" htmlType="submit">
            Save
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}
