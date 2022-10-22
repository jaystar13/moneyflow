import { useEffect, useState } from "react";
import {
  getAllCodeTypes,
  deleteCodeType,
  getCodeType,
  searchCodeTypes,
} from "../../api/api";
import CodeTypeList from "./codeTypeList";
import CodeTypeFormModal from "./codeTypeFormModal";
import { Typography } from "antd";

const { Title } = Typography;

export default function CodeType() {
  const [codeTypes, setCodeTypes] = useState([]);

  const [codeType, setCodeType] = useState({});

  const reRenderCodeTypeList = ({ changedCodeType, isSaveAction }) => {
    if (changedCodeType) {
      setCodeType(changedCodeType);
    }

    onLoadCodeTypes();

    if (isSaveAction) {
      setCodeType({ id: 0, name: "" });
    } else {
      setModalOpen(false);
    }
  };

  const onRemove = (id) => {
    deleteCodeTypeAction(id);
    setCodeTypes(codeTypes.filter((codeType) => codeType.id !== id));
  };

  const deleteCodeTypeAction = async (id) => {
    await deleteCodeType(id);
    alert("삭제 성공");
  };

  const onModify = async (id) => {
    const response = await getCodeType(id);
    await setCodeTypeModal(response.data);
  };

  const setCodeTypeModal = async (codeType) => {
    setCodeType(codeType);
    setModalOpen(true);
  };

  const onUpdateCodeType = (value, name) => {
    setCodeType({ ...codeType, [name]: value });
  };

  const onSearch = async (searchName) => {
    const response = await searchCodeTypes(searchName);
    setCodeTypes(response.data);
  };

  const onLoadCodeTypes = async () => {
    const respose = await getAllCodeTypes();
    setCodeTypes(respose.data);
  };

  useEffect(() => {
    onLoadCodeTypes();
  }, []);

  const [modalOpen, setModalOpen] = useState(false);

  const onAdd = () => {
    setCodeType({ id: 0, name: "" });
    setModalOpen(true);
  };

  return (
    <div>
      <Title>Code Type</Title>
      <CodeTypeFormModal
        codeType={codeType}
        onUpdateCodeType={onUpdateCodeType}
        modalOpen={modalOpen}
        reRenderCodeTypeList={reRenderCodeTypeList}
      />

      <CodeTypeList
        codeTypes={codeTypes}
        onRemove={onRemove}
        onModify={onModify}
        onSearch={onSearch}
        onAdd={onAdd}
      />
    </div>
  );
}
