"use client";

import {Separator} from "@/components/ui/separator";
import GroupDataTable from "@/app/admin/groups/components/group-data-table";

export default function Page() {
  return (
    <div className="space-y-6">
      <div>
        <h3 className="text-lg font-medium">그룹</h3>
        <p className="text-sm text-muted-foreground">
          그룹 관리
        </p>
      </div>
      <Separator/>
      <div className="space-y-6">
        <GroupDataTable/>
      </div>
    </div>
  )
}
