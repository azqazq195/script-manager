import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion"
import Link from "next/link";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";


export default function Page() {
  return (
      <div className="w-full flex justify-center items-center flex-col min-h-screen bg-[#f5f5f]">
        <div className={"flex flex-col gap-y-4 bg-slate-200 p-10 rounded-lg"}>
          <p className={"text-center"}>Deploy Manager</p>
          <Input type={"text"} placeholder={"Name"}/>
          <Input type={"input"} placeholder={"Password"}/>
          <Link href="/main">
            <Button className={"w-full"}>
              Login
            </Button>
          </Link>
          <Accordion type="single" collapsible>
            <AccordionItem value="item-1">
              <AccordionTrigger>How to Sign Up?</AccordionTrigger>
              <AccordionContent>
                {/* eslint-disable-next-line react/no-unescaped-entities */}
                You don't
              </AccordionContent>
            </AccordionItem>
          </Accordion>
        </div>
      </div>
  )
}
