export type Project = {
  id: number
  name: string
  description: string
  createdDate: string
}

export const projectSample: Project[] = [
  {
    id: 1,
    name: "Ezis APM",
    description: "실시간 서버 사용량 분석",
    createdDate: "2023.11.3"
  }, {
    id: 2,
    name: "ZConverter",
    description: "데이터베이스 상태 관리 매니저",
    createdDate: "2023.11.3"
  }, {
    id: 3,
    name: "Rising X",
    description: "코인 시세 분석",
    createdDate: "2023.11.3"
  },
]