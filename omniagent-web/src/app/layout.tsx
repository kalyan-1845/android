import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "OmniAgent | Professional AI Business Suite",
  description: "Next-generation offline-first AI forensics and business analysis.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        {children}
      </body>
    </html>
  );
}
