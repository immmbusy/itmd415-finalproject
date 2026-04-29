
---

## `SECURITY.md`

```md
# Security (RBAC)

## Roles
- `USER`: read-only access to department and employee list pages
- `ADMIN`: can access edit page and perform update operations

## Users (Payara file realm)
Create these users in the Payara file realm:

- `user1` in group `USER`
- `admin1` in group `ADMIN`

## What is Protected
Declarative security is configured in `WEB-INF/web.xml`:

- Allowed to `USER` and `ADMIN`:
  - `/departments.xhtml`
  - `/employees.xhtml`

- Allowed to `ADMIN` only:
  - `/editEmployee.xhtml`

Additionally, write operations are protected in the service layer:
- `EmployeeService.updateTitleAndSalary(...)` is annotated with `@RolesAllowed("ADMIN")`

## How to Test
1. Login as `user1`:
   - Can access:
     - `.../departments.xhtml`
     - `.../employees.xhtml`
   - Denied:
     - `.../editEmployee.xhtml?empId=101` (should get 403/unauthorized)

2. Login as `admin1`:
   - Can access edit page:
     - `.../editEmployee.xhtml?empId=101`
   - Can save changes (title/salary) successfully