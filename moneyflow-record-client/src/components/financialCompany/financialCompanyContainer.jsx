import { useState } from "react";
import FinancialCompanyForm from "./financialCompanyForm";
import FinancialCompanyList from "./financialCompanyList";
import { Typography } from "antd";

const { Title } = Typography;

export default function FinancialCompanyContainer() {
  const [modalConfigure, setModalConfigure] = useState({
    useModal: true,
    modalOpen: false,
  });

  const [financialCompany, setFinancialCompany] = useState({ id: 0 });

  const [render, setRender] = useState(false);

  const showFormLayer = (modifyData) => {
    setFinancialCompany(modifyData ? modifyData : { id: 0 });

    if (modalConfigure.useModal) {
      setModalConfigure({ ...modalConfigure, modalOpen: true });
      setRender(!render);
    }
  };

  return (
    <div>
      <Title>금융기관</Title>
      <FinancialCompanyForm
        configure={modalConfigure}
        data={financialCompany}
        reRender={{ render: render, setRender: setRender }}
        callbackOnAdd={showFormLayer}
      />
      <FinancialCompanyList
        callbackModfy={showFormLayer}
        reRender={{ render: render, setRender: setRender }}
      />
    </div>
  );
}
