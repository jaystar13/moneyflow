import { Typography } from "antd";
import CodeForm from "./codeForm";
import CodeList from "./codeList";
import { useState } from "react";

const { Title } = Typography;

export default function CodeContainer() {
  const [modalConfigure, setModalConfigure] = useState({
    useModal: true,
    modalOpen: false,
  });

  const [render, setRender] = useState(false);

  const onAdd = () => {
    setModalConfigure({ ...modalConfigure, modalOpen: true });
  };

  const [code, setCode] = useState({ id: 0, name: "", codeTypeId: null });

  const setCodeForm = (targetCode) => {
    if (targetCode) {
      setCode({ ...targetCode });
    }

    setModalConfigure({ ...modalConfigure, modalOpen: true });
  };

  return (
    <div>
      <Title>Code</Title>
      <CodeForm
        configure={modalConfigure}
        code={code}
        reRender={{ render: render, setRender: setRender }}
      />
      <CodeList
        setCodeForm={setCodeForm}
        reRender={{ render: render, setRender: setRender }}
      />
    </div>
  );
}
