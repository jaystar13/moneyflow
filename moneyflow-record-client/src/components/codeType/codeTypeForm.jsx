import { useNavigate } from "react-router-dom";

export default function CodeTypeForm({ codeType, onUpdateCodeType, onCreate }) {
  const navigate = useNavigate();

  const addCodeType = () => {
    onCreate(codeType);
  };

  const onChange = (e) => {
    const { value, name } = e.target;
    onUpdateCodeType(value, name);
  };

  return (
    <div>
      <h1>{codeType.id === 0 ? "Add Code Type" : "Modify Code Type"}</h1>
      <p>
        <input
          placeholder="명칭"
          type="text"
          name="name"
          value={codeType.name}
          onChange={onChange}
        />
        <button type="submit" onClick={addCodeType}>
          Save
        </button>
        <button
          onClick={() => {
            navigate(-1);
          }}
        >
          Cancel
        </button>
      </p>
    </div>
  );
}
