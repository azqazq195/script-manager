import React, {useEffect, useState} from 'react';
import {columns} from "@/app/admin/groups/components/columns";
import {DataTable} from "@/components/data-table";
import {Input} from "@/components/ui/input";
import {Group, groupSample} from "@/data/group";
import CreateGroupDialog from "@/app/admin/groups/components/create-group-dialog";

async function getGroupData(filter: string): Promise<Group[]> {
  if (!filter.trim()) {
    return groupSample;
  }

  const lowerCaseFilter = filter.toLowerCase();

  return groupSample.filter(group =>
    group.name.toLowerCase().includes(lowerCaseFilter)
  );
}

export default function GroupDataTable() {
  const [filter, setFilter] = useState<string>("");
  const [data, setData] = useState<Group[]>([]);

  useEffect(() => {
    const fetchFilteredData = () => {
      getGroupData(filter)
        .then(filteredData => {
          setData(filteredData);
        })
        .catch(error => {
          console.error("데이터를 가져오는 중 오류가 발생했습니다:", error);
        });
    };

    fetchFilteredData();
  }, [filter]);

  const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(event.target.value);
  };

  return (
    <div>
      <div className="flex items-center py-4">
        <Input
          placeholder="검색"
          value={filter}
          onChange={handleFilterChange}
          className={"max-w-sm"}
          style={{boxShadow: '0 0 0'}}
        />
        <CreateGroupDialog/>
      </div>
      <DataTable columns={columns} data={data}/>
    </div>
  )
};