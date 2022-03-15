import { Component, OnInit } from '@angular/core';
import {StudentService} from "../../service/student.service";
import {Student} from "../../model/student";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students: Student[] = [];
  constructor(private student: StudentService) { }

  ngOnInit(): void {
    this.getAllStudent()
  }

  getAllStudent(){
    this.student.getStudents().subscribe(
      data =>{
        this.students = data
      }
    )
  }

}
