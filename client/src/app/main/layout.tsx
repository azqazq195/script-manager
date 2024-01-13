import {Metadata} from "next"
import {Separator} from "@/components/ui/separator";
import {SidebarNav} from "@/app/main/components/sidebar-nav";
import {ScrollArea} from "@/components/ui/scroll-area";


export const metadata: Metadata = {
  title: "Forms",
  description: "Advanced form example using react-hook-form and Zod.",
}

const sidebarNavItems = [
  {
    title: "Profile",
    href: "/main/profile",
  },
  {
    title: "Account",
    href: "/main/account",
  },
  {
    title: "Appearance",
    href: "/main/appearance",
  },
  {
    title: "Notifications",
    href: "/main/notifications",
  },
  {
    title: "Display",
    href: "/main/display",
  },
]

interface SettingsLayoutProps {
  children: React.ReactNode
}

export default function SettingsLayout({children}: SettingsLayoutProps) {
  return (
    <div className="container relative py-20">
      <div className="overflow-hidden rounded-[0.5rem] border bg-background shadow-md md:shadow-xl">
        <div className="hidden space-y-6 p-10 pb-16 md:block">
          <div className="space-y-0.5">
            <h2 className="text-2xl font-bold tracking-tight">Settings</h2>
            <p className="text-muted-foreground">
              Manage your account settings and set e-mail preferences.
            </p>
          </div>
          <Separator className="my-6"/>
          <div className="flex flex-col space-y-8 lg:flex-row lg:space-x-12 lg:space-y-0">
            <aside className="-mx-4 lg:w-1/5">
              <SidebarNav items={sidebarNavItems}/>
            </aside>
            <ScrollArea className={"rounded-md border p-8 w-4/5 flex-auto"}>
              {children}
            </ScrollArea>
          </div>
        </div>
      </div>
    </div>

  )
}
